#ifndef __X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H
#define __X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE__REMOTEFINISH_H_NODEPS
#include <x10/lang/FinishState__RemoteFinish.h>
#undef X10_LANG_FINISHSTATE__REMOTEFINISH_H_NODEPS
#define X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
#include <x10/lang/FinishState__CollectingFinishState.h>
#undef X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__StatefulReducer;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Reducible;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(Z2), class FMGL(U)> class Fun_0_2;
} } 
namespace x10 { namespace lang { 
class MultipleExceptions;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__RootCollectingFinish;
} } 
namespace x10 { namespace compiler { 
class RemoteInvocation;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 

template<class FMGL(T)> class FinishState__RemoteCollectingFinish;
template <> class FinishState__RemoteCollectingFinish<void>;
template<class FMGL(T)> class FinishState__RemoteCollectingFinish : public x10::lang::FinishState__RemoteFinish
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::lang::FinishState__CollectingFinishState<FMGL(T)>::template itable<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> >
      FMGL(sr);
    
    void _constructor(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref,
                      x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer);
    
    static x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > _make(
             x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref,
             x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer);
    
    virtual void accept(FMGL(T) t, x10_int id);
    virtual void notifyActivityTermination();
    virtual x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >
      x10__lang__FinishState__RemoteCollectingFinish____x10__lang__FinishState__RemoteCollectingFinish__this(
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
template <> class FinishState__RemoteCollectingFinish<void> : public x10::lang::FinishState__RemoteFinish
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H

namespace x10 { namespace lang { 
template<class FMGL(T)>
class FinishState__RemoteCollectingFinish;
} } 

#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_NODEPS
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_NODEPS
#include <x10/lang/FinishState__RemoteFinish.h>
#include <x10/lang/FinishState__CollectingFinishState.h>
#include <x10/lang/FinishState__StatefulReducer.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/Reducible.h>
#include <x10/lang/Int.h>
#include <x10/lang/Fun_0_2.h>
#include <x10/lang/MultipleExceptions.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Place.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Rail.h>
#include <x10/lang/FinishState__RootCollectingFinish.h>
#include <x10/compiler/RemoteInvocation.h>
#include <x10/lang/Rail.h>
#include <x10/util/Pair.h>
#include <x10/lang/Fun_0_1.h>
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__0_CLOSURE
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_FinishState__RemoteCollectingFinish__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 659 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::FinishState::template deref<x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > >(
                            ref))->notify(message, x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(t));
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref;
    x10aux::ref<x10::lang::Rail<x10_int > > message;
    x10aux::ref<x10::lang::MultipleExceptions> t;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->ref);
        buf.write(this->message);
        buf.write(this->t);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > that_ref = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >();
        x10aux::ref<x10::lang::Rail<x10_int > > that_message = buf.read<x10aux::ref<x10::lang::Rail<x10_int > > >();
        x10aux::ref<x10::lang::MultipleExceptions> that_t = buf.read<x10aux::ref<x10::lang::MultipleExceptions> >();
        x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >(that_ref, that_message, that_t);
        return this_;
    }
    
    x10_lang_FinishState__RemoteCollectingFinish__closure__0(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref, x10aux::ref<x10::lang::Rail<x10_int > > message, x10aux::ref<x10::lang::MultipleExceptions> t) : ref(ref), message(message), t(t) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:659";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) > >x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >::__apply, &x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_GENERAL_ASYNC);

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__0_CLOSURE
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__1_CLOSURE
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__1_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_FinishState__RemoteCollectingFinish__closure__1 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 661 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::FinishState::template deref<x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > >(
                            ref))->notifyValue(message, result);
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref;
    x10aux::ref<x10::lang::Rail<x10_int > > message;
    FMGL(T) result;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->ref);
        buf.write(this->message);
        buf.write(this->result);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > that_ref = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >();
        x10aux::ref<x10::lang::Rail<x10_int > > that_message = buf.read<x10aux::ref<x10::lang::Rail<x10_int > > >();
        FMGL(T) that_result = buf.read<FMGL(T)>();
        x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >(that_ref, that_message, that_result);
        return this_;
    }
    
    x10_lang_FinishState__RemoteCollectingFinish__closure__1(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref, x10aux::ref<x10::lang::Rail<x10_int > > message, FMGL(T) result) : ref(ref), message(message), result(result) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:661";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) > >x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >::__apply, &x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_GENERAL_ASYNC);

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__1_CLOSURE
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__2_CLOSURE
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__2_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(T)> class x10_lang_FinishState__RemoteCollectingFinish__closure__2 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> >::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10::util::Pair<x10_int, x10_int> __apply(x10_int i) {
        
        //#line 664 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
        return x10::util::Pair_methods<x10_int, x10_int>::_make((saved_this->
                                                                   FMGL(places))->__apply(i),
                                                                (saved_this->
                                                                   FMGL(counts))->__apply((saved_this->
                                                                                             FMGL(places))->__apply(i)));
        
    }
    
    // captured environment
    x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) > >(storage));
        x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > >();
        x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >(that_saved_this);
        return this_;
    }
    
    x10_lang_FinishState__RemoteCollectingFinish__closure__2(x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:664";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> >::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) > >x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >::__apply, &x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> > >, &x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__2_CLOSURE
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__3_CLOSURE
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__3_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_FinishState__RemoteCollectingFinish__closure__3 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 666 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::FinishState::template deref<x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > >(
                            ref))->notify(message, x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(t));
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref;
    x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message;
    x10aux::ref<x10::lang::MultipleExceptions> t;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->ref);
        buf.write(this->message);
        buf.write(this->t);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > that_ref = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >();
        x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > that_message = buf.read<x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > >();
        x10aux::ref<x10::lang::MultipleExceptions> that_t = buf.read<x10aux::ref<x10::lang::MultipleExceptions> >();
        x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >(that_ref, that_message, that_t);
        return this_;
    }
    
    x10_lang_FinishState__RemoteCollectingFinish__closure__3(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref, x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message, x10aux::ref<x10::lang::MultipleExceptions> t) : ref(ref), message(message), t(t) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:666";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) > >x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >::__apply, &x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_GENERAL_ASYNC);

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__3_CLOSURE
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__4_CLOSURE
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__4_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_FinishState__RemoteCollectingFinish__closure__4 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 668 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::FinishState::template deref<x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > >(
                            ref))->notifyValue(message, result);
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref;
    x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message;
    FMGL(T) result;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->ref);
        buf.write(this->message);
        buf.write(this->result);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > that_ref = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >();
        x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > that_message = buf.read<x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > >();
        FMGL(T) that_result = buf.read<FMGL(T)>();
        x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >(that_ref, that_message, that_result);
        return this_;
    }
    
    x10_lang_FinishState__RemoteCollectingFinish__closure__4(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref, x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message, FMGL(T) result) : ref(ref), message(message), result(result) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:668";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) > >x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >::__apply, &x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_GENERAL_ASYNC);

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__4_CLOSURE
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__5_CLOSURE
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__5_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_FinishState__RemoteCollectingFinish__closure__5 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 676 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::FinishState::template deref<x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > >(
                            ref))->notify(message, x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(t));
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref;
    x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message;
    x10aux::ref<x10::lang::MultipleExceptions> t;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->ref);
        buf.write(this->message);
        buf.write(this->t);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > that_ref = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >();
        x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > that_message = buf.read<x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > >();
        x10aux::ref<x10::lang::MultipleExceptions> that_t = buf.read<x10aux::ref<x10::lang::MultipleExceptions> >();
        x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >(that_ref, that_message, that_t);
        return this_;
    }
    
    x10_lang_FinishState__RemoteCollectingFinish__closure__5(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref, x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message, x10aux::ref<x10::lang::MultipleExceptions> t) : ref(ref), message(message), t(t) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:676";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) > >x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >::__apply, &x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_GENERAL_ASYNC);

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__5_CLOSURE
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__6_CLOSURE
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__6_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_FinishState__RemoteCollectingFinish__closure__6 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 678 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(x10::lang::FinishState::template deref<x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > >(
                            ref))->notifyValue(message, result);
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref;
    x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message;
    FMGL(T) result;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->ref);
        buf.write(this->message);
        buf.write(this->result);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > that_ref = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >();
        x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > that_message = buf.read<x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > >();
        FMGL(T) that_result = buf.read<FMGL(T)>();
        x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >(that_ref, that_message, that_result);
        return this_;
    }
    
    x10_lang_FinishState__RemoteCollectingFinish__closure__6(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref, x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message, FMGL(T) result) : ref(ref), message(message), result(result) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:678";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) > >x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >::__apply, &x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_GENERAL_ASYNC);

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH__CLOSURE__6_CLOSURE
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_GENERICS
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>))) x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_GENERICS
#ifndef X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_IMPLEMENTATION
#define X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_IMPLEMENTATION
#include <x10/lang/FinishState__RemoteCollectingFinish.h>


template<class FMGL(T)> typename x10::lang::FinishState__CollectingFinishState<FMGL(T)>::template itable<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >  x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_itable_0(&x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::accept, &x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >  x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_itable_1(&x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >())};
template<class FMGL(T)> void x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>");
    
}


//#line 633 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10FieldDecl_c

//#line 634 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_constructor(
                          x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref,
                          x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer)
{
    this->::x10::lang::FinishState__RemoteFinish::_constructor(
      ref);
    {
     
    }
    
    //#line 636 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
      FMGL(sr) =
      x10::lang::FinishState__StatefulReducer<FMGL(T)>::_make(reducer);
    
}
template<class FMGL(T)>
x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_make(
  x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref,
  x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer)
{
    x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>))) x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>();
    this_->_constructor(ref,
    reducer);
    return this_;
}



//#line 638 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::accept(
  FMGL(T) t,
  x10_int id) {
    
    //#line 639 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->accept(
      t,
      id);
}

//#line 641 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::notifyActivityTermination(
  ) {
    
    //#line 642 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                        FMGL(lock))->lock();
    
    //#line 643 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    (__extension__ ({
        x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > x =
          ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this);
        x10_int y =
          ((x10_int)1);
        x10aux::nullCheck(x)->
          FMGL(count) =
          ((x10_int) ((x10aux::nullCheck(x)->
                         FMGL(count)) - (y)));
    }))
    ;
    
    //#line 644 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
    if (((((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
            FMGL(local)->x10::util::concurrent::AtomicInteger::decrementAndGet()) > (((x10_int)0))))
    {
        
        //#line 645 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                            FMGL(lock))->unlock();
        
        //#line 646 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
        return;
    }
    
    //#line 648 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::MultipleExceptions> t =
      x10::lang::MultipleExceptions::make(
        ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
          FMGL(exceptions));
    
    //#line 649 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref =
      ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->ref();
    
    //#line 650 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::VoidFun_0_0> closure;
    
    //#line 651 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->placeMerge();
    
    //#line 652 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
    FMGL(T) result = x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                         FMGL(sr))->result();
    
    //#line 653 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                        FMGL(sr))->reset();
    
    //#line 654 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(X10_NULL,
                                ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                  FMGL(counts))))
    {
        
        //#line 655 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        (((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
           FMGL(counts))->__set(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                  FMGL(count), x10aux::here);
        
        //#line 656 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
        if (((((x10_int) ((((x10_int)2)) * (((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                              FMGL(length))))) > (x10aux::num_hosts)))
        {
            
            //#line 657 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Rail<x10_int > > message =
              x10::lang::Rail<void>::make<x10_int >(x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                                                        FMGL(counts))->
                                                      FMGL(length), ((x10_int)0), ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                                                                    FMGL(counts));
            
            //#line 658 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        t)))
            {
                
                //#line 659 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
                closure =
                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T)>)))x10_lang_FinishState__RemoteCollectingFinish__closure__0<FMGL(T)>(ref, message, t))));
            }
            else
            {
                
                //#line 661 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
                closure =
                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T)>)))x10_lang_FinishState__RemoteCollectingFinish__closure__1<FMGL(T)>(ref, message, result))));
            }
            
        }
        else
        {
            
            //#line 664 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message =
              x10::lang::Rail<void>::make<x10::util::Pair<x10_int, x10_int> >(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                                                                FMGL(length), x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> > > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> > >(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, x10::util::Pair<x10_int, x10_int> > >(sizeof(x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T)>)))x10_lang_FinishState__RemoteCollectingFinish__closure__2<FMGL(T)>(this)))));
            
            //#line 665 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(X10_NULL,
                                        t)))
            {
                
                //#line 666 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
                closure =
                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T)>)))x10_lang_FinishState__RemoteCollectingFinish__closure__3<FMGL(T)>(ref, message, t))));
            }
            else
            {
                
                //#line 668 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
                closure =
                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T)>)))x10_lang_FinishState__RemoteCollectingFinish__closure__4<FMGL(T)>(ref, message, result))));
            }
            
        }
        
        //#line 671 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        (((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
           FMGL(counts))->reset(((x10_int)0));
        
        //#line 672 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
          FMGL(length) =
          ((x10_int)1);
    }
    else
    {
        
        //#line 674 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::lang::Rail<x10::util::Pair<x10_int, x10_int> > > message =
          x10::lang::Rail<void>::make<x10::util::Pair<x10_int, x10_int> >(((x10_int)1), x10::util::Pair_methods<x10_int, x10_int>::_make(x10aux::here,
                                                                                                                                         ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                                                                                                                                           FMGL(count)));
        
        //#line 675 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
        if ((!x10aux::struct_equals(X10_NULL,
                                    t)))
        {
            
            //#line 676 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
            closure =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T)>)))x10_lang_FinishState__RemoteCollectingFinish__closure__5<FMGL(T)>(ref, message, t))));
        }
        else
        {
            
            //#line 678 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
            closure =
              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T)>)))x10_lang_FinishState__RemoteCollectingFinish__closure__6<FMGL(T)>(ref, message, result))));
        }
        
    }
    
    //#line 681 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
      FMGL(count) = ((x10_int)0);
    
    //#line 682 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
      FMGL(exceptions) = x10aux::class_cast_unchecked<x10aux::ref<x10::util::Stack<x10aux::ref<x10::lang::Throwable> > > >(X10_NULL);
    
    //#line 683 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this)->
                        FMGL(lock))->unlock();
    
    //#line 684 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::run_closure_at(x10::lang::Place_methods::place((ref)->location)->
                             FMGL(id), closure);
    
    //#line 685 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10aux::dealloc(closure.operator->());
}

//#line 632 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >
  x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::x10__lang__FinishState__RemoteCollectingFinish____x10__lang__FinishState__RemoteCollectingFinish__this(
  ) {
    
    //#line 632 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::FinishState__RemoteFinish::_serialize_body(buf);
    buf.write(this->FMGL(sr));
    
}

template<class FMGL(T)> void x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::FinishState__RemoteFinish::_deserialize_body(buf);
    FMGL(sr) = buf.read<x10aux::ref<x10::lang::FinishState__StatefulReducer<FMGL(T)> > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::FinishState__RemoteCollectingFinish<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::FinishState__RemoteFinish>(), x10aux::getRTT<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.FinishState.RemoteCollectingFinish";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
#endif // X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_IMPLEMENTATION
#endif // __X10_LANG_FINISHSTATE__REMOTECOLLECTINGFINISH_H_NODEPS
