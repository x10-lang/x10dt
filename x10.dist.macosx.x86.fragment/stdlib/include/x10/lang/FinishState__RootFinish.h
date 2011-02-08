#ifndef __X10_LANG_FINISHSTATE__ROOTFINISH_H
#define __X10_LANG_FINISHSTATE__ROOTFINISH_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_NODEPS
#include <x10/lang/FinishState__RootFinishSkeleton.h>
#undef X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_NODEPS
#define X10_LANG_SIMPLELATCH_H_NODEPS
#include <x10/lang/SimpleLatch.h>
#undef X10_LANG_SIMPLELATCH_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace compiler { 
class Embed;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class Stack;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace compiler { 
class RemoteInvocation;
} } 
namespace x10 { namespace lang { 
class MultipleExceptions;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(Z3), class FMGL(U)> class Fun_0_3;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 

class FinishState__RootFinish : public x10::lang::FinishState__RootFinishSkeleton
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Runtime__Mortal::itable<x10::lang::FinishState__RootFinish > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::FinishState__RootFinish > _itable_1;
    
    virtual x10_boolean _isMortal() { return true; }
    
    void _instance_init();
    
    x10::lang::SimpleLatch _Embed_latch;
    x10aux::ref<x10::lang::SimpleLatch> FMGL(latch);
    
    x10_int FMGL(count);
    
    x10aux::ref<x10::util::Stack<x10aux::ref<x10::lang::Throwable> > >
      FMGL(exceptions);
    
    x10aux::ref<x10::lang::Rail<x10_int > > FMGL(counts);
    
    x10aux::ref<x10::lang::Rail<x10_boolean > > FMGL(seen);
    
    void _constructor();
    
    static x10aux::ref<x10::lang::FinishState__RootFinish> _make(
             );
    
    void _constructor(x10aux::ref<x10::lang::SimpleLatch> latch);
    
    static x10aux::ref<x10::lang::FinishState__RootFinish> _make(
             x10aux::ref<x10::lang::SimpleLatch> latch);
    
    virtual void notifySubActivitySpawn(x10::lang::Place place);
    virtual void notifyActivityTermination();
    virtual void process(x10aux::ref<x10::lang::Throwable> t);
    virtual void pushException(x10aux::ref<x10::lang::Throwable> t);
    virtual void waitForFinish();
    virtual void process(x10aux::ref<x10::lang::Rail<x10_int > > rail);
    virtual void notify(x10aux::ref<x10::lang::Rail<x10_int > > rail);
    virtual void process(x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > rail);
    virtual void notify(x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > rail);
    virtual void notify(x10aux::ref<x10::lang::Rail<x10_int > > rail,
                        x10aux::ref<x10::lang::Throwable> t);
    virtual void notify(x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > rail,
                        x10aux::ref<x10::lang::Throwable> t);
    virtual x10aux::ref<x10::lang::SimpleLatch> simpleLatch(
      );
    virtual x10aux::ref<x10::lang::FinishState__RootFinish>
      x10__lang__FinishState__RootFinish____x10__lang__FinishState__RootFinish__this(
      );
    void __fieldInitializers1929();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_FINISHSTATE__ROOTFINISH_H

namespace x10 { namespace lang { 
class FinishState__RootFinish;
} } 

#ifndef X10_LANG_FINISHSTATE__ROOTFINISH_H_NODEPS
#define X10_LANG_FINISHSTATE__ROOTFINISH_H_NODEPS
#include <x10/lang/FinishState__RootFinishSkeleton.h>
#include <x10/lang/SimpleLatch.h>
#include <x10/compiler/Embed.h>
#include <x10/lang/Int.h>
#include <x10/util/Stack.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Place.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_3.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/compiler/RemoteInvocation.h>
#include <x10/lang/MultipleExceptions.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/Fun_0_3.h>
#include <x10/lang/Fun_0_3.h>
#include <x10/lang/Rail.h>
#include <x10/util/Pair.h>
#include <x10/lang/Fun_0_3.h>
#include <x10/lang/Fun_0_2.h>
#ifndef X10_LANG_FINISHSTATE__ROOTFINISH_H_GENERICS
#define X10_LANG_FINISHSTATE__ROOTFINISH_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::FinishState__RootFinish::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__RootFinish> this_ = new (memset(x10aux::alloc<x10::lang::FinishState__RootFinish>(), 0, sizeof(x10::lang::FinishState__RootFinish))) x10::lang::FinishState__RootFinish();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__ROOTFINISH_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__ROOTFINISH_H_NODEPS
