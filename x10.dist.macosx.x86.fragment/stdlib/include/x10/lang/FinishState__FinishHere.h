#ifndef __X10_LANG_FINISHSTATE__FINISHHERE_H
#define __X10_LANG_FINISHSTATE__FINISHHERE_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE__FINISHSKELETON_H_NODEPS
#include <x10/lang/FinishState__FinishSkeleton.h>
#undef X10_LANG_FINISHSTATE__FINISHSKELETON_H_NODEPS
#define X10_IO_CUSTOMSERIALIZATION_H_NODEPS
#include <x10/io/CustomSerialization.h>
#undef X10_IO_CUSTOMSERIALIZATION_H_NODEPS
namespace x10 { namespace lang { 
class FinishState__RootFinishSkeleton;
} } 
namespace x10 { namespace lang { 
class FinishState__RootFinishSPMD;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 

class FinishState__FinishHere : public x10::lang::FinishState__FinishSkeleton
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::io::CustomSerialization::itable<x10::lang::FinishState__FinishHere > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::FinishState__FinishHere > _itable_1;
    
    void _instance_init();
    
    void _constructor();
    
    static x10aux::ref<x10::lang::FinishState__FinishHere> _make();
    
    void _constructor(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref);
    
    static x10aux::ref<x10::lang::FinishState__FinishHere> _make(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref);
    
    void _constructor(x10aux::ref<x10::io::SerialData> data);
    
    static x10aux::ref<x10::lang::FinishState__FinishHere> _make(x10aux::ref<x10::io::SerialData> data);
    
    virtual x10aux::ref<x10::lang::FinishState__FinishHere> x10__lang__FinishState__FinishHere____x10__lang__FinishState__FinishHere__this(
      );
    
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
#endif // X10_LANG_FINISHSTATE__FINISHHERE_H

namespace x10 { namespace lang { 
class FinishState__FinishHere;
} } 

#ifndef X10_LANG_FINISHSTATE__FINISHHERE_H_NODEPS
#define X10_LANG_FINISHSTATE__FINISHHERE_H_NODEPS
#include <x10/lang/FinishState__FinishSkeleton.h>
#include <x10/io/CustomSerialization.h>
#include <x10/lang/FinishState__RootFinishSkeleton.h>
#include <x10/lang/FinishState__RootFinishSPMD.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/FinishState.h>
#include <x10/io/SerialData.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#ifndef X10_LANG_FINISHSTATE__FINISHHERE_H_GENERICS
#define X10_LANG_FINISHSTATE__FINISHHERE_H_GENERICS
template<class __T> x10aux::ref<__T> x10::lang::FinishState__FinishHere::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__FinishHere> this_ = new (memset(x10aux::alloc<x10::lang::FinishState__FinishHere>(), 0, sizeof(x10::lang::FinishState__FinishHere))) x10::lang::FinishState__FinishHere();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__FINISHHERE_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__FINISHHERE_H_NODEPS
