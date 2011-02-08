#ifndef __X10_UTIL_FUTURE_H
#define __X10_UTIL_FUTURE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_FUN_0_0_H_NODEPS
#include <x10/lang/Fun_0_0.h>
#undef X10_LANG_FUN_0_0_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class Latch;
} } 
namespace x10 { namespace compiler { 
class SuppressTransientError;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class GrowableRail;
} } 
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace util { 
template<class FMGL(T)> class GrowableRail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace compiler { 
class AsyncClosure;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace compiler { 
class Global;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace compiler { 
class Pinned;
} } 
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class RuntimeException;
} } 
namespace x10 { namespace compiler { 
class Finalization;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class GrowableRail;
} } 
namespace x10 { namespace util { 

template<class FMGL(T)> class Future;
template <> class Future<void>;
template<class FMGL(T)> class Future : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static typename x10::lang::Fun_0_0<FMGL(T)>::template itable<x10::util::Future<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::util::Future<FMGL(T)> > _itable_1;
    
    void _instance_init();
    
    x10::lang::GlobalRef<x10aux::ref<x10::util::Future<FMGL(T)> > > FMGL(root);
    
    x10aux::ref<x10::lang::Latch> FMGL(latch);
    
    x10aux::ref<x10::util::GrowableRail<x10aux::ref<x10::lang::Throwable> > >
      FMGL(exception);
    
    x10aux::ref<x10::util::GrowableRail<FMGL(T) > > FMGL(result);
    
    x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > FMGL(eval);
    
    void _constructor(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval);
    
    static x10aux::ref<x10::util::Future<FMGL(T)> > _make(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval);
    
    FMGL(T) result();
    virtual x10_boolean forced();
    virtual FMGL(T) __apply();
    virtual FMGL(T) force();
    FMGL(T) forceLocal();
    virtual void run();
    virtual x10aux::ref<x10::util::Future<FMGL(T)> > x10__util__Future____x10__util__Future__this(
      );
    void __fieldInitializers2089();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class Future<void> : public x10::lang::Object {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    template<class FMGL(T)> static x10aux::ref<x10::util::Future<FMGL(T)> >
      make(
      x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval);
    
    
};

} } 
#endif // X10_UTIL_FUTURE_H

namespace x10 { namespace util { 
template<class FMGL(T)> class Future;
} } 

#ifndef X10_UTIL_FUTURE_H_NODEPS
#define X10_UTIL_FUTURE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/Latch.h>
#include <x10/compiler/SuppressTransientError.h>
#include <x10/util/GrowableRail.h>
#include <x10/lang/Throwable.h>
#include <x10/util/GrowableRail.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/VoidFun_0_0.h>
#include <x10/compiler/AsyncClosure.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/compiler/Global.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/compiler/Pinned.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/RuntimeException.h>
#include <x10/compiler/Finalization.h>
#include <x10/lang/GlobalRef.h>
#include <x10/util/GrowableRail.h>
#ifndef X10_UTIL_FUTURE__CLOSURE__0_CLOSURE
#define X10_UTIL_FUTURE__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_util_Future__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_util_Future__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    FMGL(T) __apply() {
        
        //#line 55 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
        return (x10aux::nullCheck((saved_this->FMGL(root))->__apply())->FMGL(result))->__apply(((x10_int)0));
        
    }
    
    // captured environment
    x10aux::ref<x10::util::Future<FMGL(T)> > saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_Future__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_util_Future__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_Future__closure__0<FMGL(T) > >(storage));
        x10aux::ref<x10::util::Future<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::util::Future<FMGL(T)> > >();
        x10aux::ref<x10_util_Future__closure__0<FMGL(T) > > this_ = new (storage) x10_util_Future__closure__0<FMGL(T) >(that_saved_this);
        return this_;
    }
    
    x10_util_Future__closure__0(x10aux::ref<x10::util::Future<FMGL(T)> > saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10:55";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_util_Future__closure__0<FMGL(T) > >x10_util_Future__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_Future__closure__0<FMGL(T) >::__apply, &x10_util_Future__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_Future__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >, &x10_util_Future__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_Future__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_Future__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_FUTURE__CLOSURE__0_CLOSURE
#ifndef X10_UTIL_FUTURE__CLOSURE__1_CLOSURE
#define X10_UTIL_FUTURE__CLOSURE__1_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_util_Future__closure__1 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10_boolean>::template itable <x10_util_Future__closure__1<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10_boolean __apply() {
        
        //#line 60 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
        return x10aux::nullCheck((saved_this->FMGL(root))->__apply())->FMGL(latch)->__apply();
        
    }
    
    // captured environment
    x10aux::ref<x10::util::Future<FMGL(T)> > saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_Future__closure__1<FMGL(T) >* storage = x10aux::alloc<x10_util_Future__closure__1<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_Future__closure__1<FMGL(T) > >(storage));
        x10aux::ref<x10::util::Future<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::util::Future<FMGL(T)> > >();
        x10aux::ref<x10_util_Future__closure__1<FMGL(T) > > this_ = new (storage) x10_util_Future__closure__1<FMGL(T) >(that_saved_this);
        return this_;
    }
    
    x10_util_Future__closure__1(x10aux::ref<x10::util::Future<FMGL(T)> > saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10_boolean> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10_boolean> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10:60";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10_boolean>::template itable <x10_util_Future__closure__1<FMGL(T) > >x10_util_Future__closure__1<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_Future__closure__1<FMGL(T) >::__apply, &x10_util_Future__closure__1<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_Future__closure__1<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10_boolean> >, &x10_util_Future__closure__1<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_Future__closure__1<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_Future__closure__1<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_FUTURE__CLOSURE__1_CLOSURE
#ifndef X10_UTIL_FUTURE__CLOSURE__2_CLOSURE
#define X10_UTIL_FUTURE__CLOSURE__2_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_util_Future__closure__2 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_util_Future__closure__2<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    FMGL(T) __apply() {
        
        //#line 68 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
        return x10aux::nullCheck((saved_this->FMGL(root))->__apply())->forceLocal();
        
    }
    
    // captured environment
    x10aux::ref<x10::util::Future<FMGL(T)> > saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_Future__closure__2<FMGL(T) >* storage = x10aux::alloc<x10_util_Future__closure__2<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_Future__closure__2<FMGL(T) > >(storage));
        x10aux::ref<x10::util::Future<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::util::Future<FMGL(T)> > >();
        x10aux::ref<x10_util_Future__closure__2<FMGL(T) > > this_ = new (storage) x10_util_Future__closure__2<FMGL(T) >(that_saved_this);
        return this_;
    }
    
    x10_util_Future__closure__2(x10aux::ref<x10::util::Future<FMGL(T)> > saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10:68";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_util_Future__closure__2<FMGL(T) > >x10_util_Future__closure__2<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_Future__closure__2<FMGL(T) >::__apply, &x10_util_Future__closure__2<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_Future__closure__2<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >, &x10_util_Future__closure__2<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_Future__closure__2<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_Future__closure__2<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_FUTURE__CLOSURE__2_CLOSURE
#ifndef X10_UTIL_FUTURE__CLOSURE__3_CLOSURE
#define X10_UTIL_FUTURE__CLOSURE__3_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_util_Future__closure__3 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_util_Future__closure__3<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
        f->run();
    }
    
    // captured environment
    x10aux::ref<x10::util::Future<FMGL(T)> > f;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->f);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_Future__closure__3<FMGL(T) >* storage = x10aux::alloc<x10_util_Future__closure__3<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_util_Future__closure__3<FMGL(T) > >(storage));
        x10aux::ref<x10::util::Future<FMGL(T)> > that_f = buf.read<x10aux::ref<x10::util::Future<FMGL(T)> > >();
        x10aux::ref<x10_util_Future__closure__3<FMGL(T) > > this_ = new (storage) x10_util_Future__closure__3<FMGL(T) >(that_f);
        return this_;
    }
    
    x10_util_Future__closure__3(x10aux::ref<x10::util::Future<FMGL(T)> > f) : f(f) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10:48";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_util_Future__closure__3<FMGL(T) > >x10_util_Future__closure__3<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_Future__closure__3<FMGL(T) >::__apply, &x10_util_Future__closure__3<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_util_Future__closure__3<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_util_Future__closure__3<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_util_Future__closure__3<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_Future__closure__3<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_SIMPLE_ASYNC);

#endif // X10_UTIL_FUTURE__CLOSURE__3_CLOSURE
#ifndef X10_UTIL_FUTURE_H_GENERICS
#define X10_UTIL_FUTURE_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::util::Future<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::Future<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::Future<FMGL(T)> >(), 0, sizeof(x10::util::Future<FMGL(T)>))) x10::util::Future<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_FUTURE_H_GENERICS
#ifndef X10_UTIL_FUTURE_H_IMPLEMENTATION
#define X10_UTIL_FUTURE_H_IMPLEMENTATION
#include <x10/util/Future.h>


template<class FMGL(T)> typename x10::lang::Fun_0_0<FMGL(T)>::template itable<x10::util::Future<FMGL(T)> >  x10::util::Future<FMGL(T)>::_itable_0(&x10::util::Future<FMGL(T)>::equals, &x10::util::Future<FMGL(T)>::hashCode, &x10::util::Future<FMGL(T)>::__apply, &x10::util::Future<FMGL(T)>::toString, &x10::util::Future<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::util::Future<FMGL(T)> >  x10::util::Future<FMGL(T)>::_itable_1(&x10::util::Future<FMGL(T)>::equals, &x10::util::Future<FMGL(T)>::hashCode, &x10::util::Future<FMGL(T)>::toString, &x10::util::Future<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::util::Future<FMGL(T)>::_itables[3] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::util::Future<FMGL(T)> >())};
template<class FMGL(T)> void x10::util::Future<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::Future<FMGL(T)>");
    
}


//#line 28 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10FieldDecl_c

//#line 32 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10FieldDecl_c
/**
     * Latch for signaling and wait
     */
                                               //#line 41 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10FieldDecl_c
                                               /**
     * Set if the activity terminated with an exception.
     * Can only be of type Error or RuntimeException
     *
     */
                                                                                                                                                                               //#line 42 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10FieldDecl_c
                                                                                                                                                                               
                                                                                                                                                                               //#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10FieldDecl_c
                                                                                                                                                                               

//#line 45 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c

//#line 51 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::util::Future<FMGL(T)>::_constructor(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval)
{
    this->::x10::lang::Object::_constructor();
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->x10::util::Future<FMGL(T)>::__fieldInitializers2089();
    
    //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
      FMGL(eval) =
      eval;
    
}
template<class FMGL(T)>
x10aux::ref<x10::util::Future<FMGL(T)> > x10::util::Future<FMGL(T)>::_make(
  x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval)
{
    x10aux::ref<x10::util::Future<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::util::Future<FMGL(T)> >(), 0, sizeof(x10::util::Future<FMGL(T)>))) x10::util::Future<FMGL(T)>();
    this_->_constructor(eval);
    return this_;
}



//#line 55 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Future<FMGL(T)>::result(
  ) {
    
    //#line 55 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
    return x10::lang::Runtime::template evalAt<FMGL(T) >(x10::lang::Place_methods::place((((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                                                                                            FMGL(root))->location),
                                                         x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> >(x10aux::ref<x10_util_Future__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<FMGL(T)> >(sizeof(x10_util_Future__closure__0<FMGL(T)>)))x10_util_Future__closure__0<FMGL(T)>(this)))));
    
}

//#line 60 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10_boolean x10::util::Future<FMGL(T)>::forced(
  ) {
    
    //#line 60 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
    return x10::lang::Runtime::template evalAt<x10_boolean >(
             x10::lang::Place_methods::place((((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                                                FMGL(root))->location),
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10_boolean> > >(x10aux::ref<x10::lang::Fun_0_0<x10_boolean> >(x10aux::ref<x10_util_Future__closure__1<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10_boolean> >(sizeof(x10_util_Future__closure__1<FMGL(T)>)))x10_util_Future__closure__1<FMGL(T)>(this)))));
    
}

//#line 61 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Future<FMGL(T)>::__apply(
  ) {
    
    //#line 61 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->force();
    
}

//#line 66 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Future<FMGL(T)>::force(
  ) {
    
    //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::ensureNotInAtomic();
    
    //#line 68 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
    return x10::lang::Runtime::template evalAt<FMGL(T) >(
             x10::lang::Place_methods::place((((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                                                FMGL(root))->location),
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> >(x10aux::ref<x10_util_Future__closure__2<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<FMGL(T)> >(sizeof(x10_util_Future__closure__2<FMGL(T)>)))x10_util_Future__closure__2<FMGL(T)>(this)))));
    
}

//#line 70 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::util::Future<FMGL(T)>::forceLocal(
  ) {
    
    //#line 71 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
      FMGL(latch)->await();
    
    //#line 72 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10If_c
    if ((((((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
             FMGL(exception))->length()) > (((x10_int)0))))
    {
        
        //#line 73 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Throw_c
        x10aux::throwException(x10aux::nullCheck((((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                                                    FMGL(exception))->__apply(((x10_int)0))));
    }
    
    //#line 75 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
              FMGL(result))->__apply(((x10_int)0));
    
}

//#line 77 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::Future<FMGL(T)>::run(
  ) {
    
    //#line 78 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Try_c
    try {
        {
            
            //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
            x10::lang::Runtime::ensureNotInAtomic();
            
            //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10LocalDecl_c
            x10aux::ref<x10::lang::FinishState> x10____var11 =
              x10::lang::Runtime::startFinish();
            {
                
                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10LocalDecl_c
                x10aux::ref<x10::lang::Throwable> throwable2090 =
                  x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Throwable> >(X10_NULL);
                
                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Try_c
                try {
                    
                    //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Try_c
                    try {
                        {
                            
                            //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
                            (((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                               FMGL(result))->add(x10::lang::Fun_0_0<FMGL(T)>::__apply(x10aux::nullCheck(((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                                                                                                           FMGL(eval))));
                        }
                    }
                    catch (x10aux::__ref& __ref__1639) {
                        x10aux::ref<x10::lang::Throwable>& __exc__ref__1639 = (x10aux::ref<x10::lang::Throwable>&)__ref__1639;
                        if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1639)) {
                            x10aux::ref<x10::lang::Throwable> __lowerer__var__11__ =
                              static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1639);
                            {
                                
                                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
                                x10::lang::Runtime::pushException(
                                  __lowerer__var__11__);
                                
                                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Throw_c
                                x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
                            }
                        } else
                        throw;
                    }
                    
                    //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
                    x10::compiler::Finalization::plausibleThrow();
                }
                catch (x10aux::__ref& __ref__1640) {
                    x10aux::ref<x10::lang::Throwable>& __exc__ref__1640 = (x10aux::ref<x10::lang::Throwable>&)__ref__1640;
                    if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1640)) {
                        x10aux::ref<x10::lang::Throwable> formal2091 =
                          static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1640);
                        {
                            
                            //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
                            throwable2090 =
                              formal2091;
                        }
                    } else
                    throw;
                }
                {
                    
                    //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
                    x10::lang::Runtime::stopFinish(
                      x10____var11);
                }
                
                //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10If_c
                if ((!x10aux::struct_equals(X10_NULL,
                                            throwable2090)))
                {
                    
                    //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10If_c
                    if (!(x10aux::instanceof<x10aux::ref<x10::compiler::Finalization> >(throwable2090)))
                    {
                        
                        //#line 79 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Throw_c
                        x10aux::throwException(x10aux::nullCheck(throwable2090));
                    }
                    
                }
                
            }
        }
        
        //#line 80 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
          FMGL(latch)->release();
    }
    catch (x10aux::__ref& __ref__1641) {
        x10aux::ref<x10::lang::Throwable>& __exc__ref__1641 = (x10aux::ref<x10::lang::Throwable>&)__ref__1641;
        if (x10aux::instanceof<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1641)) {
            x10aux::ref<x10::lang::Throwable> t =
              static_cast<x10aux::ref<x10::lang::Throwable> >(__exc__ref__1641);
            {
                
                //#line 82 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
                (((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                   FMGL(exception))->add(t);
                
                //#line 83 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
                ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
                  FMGL(latch)->release();
            }
        } else
        throw;
    }
}

//#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::util::Future<FMGL(T)> >
  x10::util::Future<FMGL(T)>::x10__util__Future____x10__util__Future__this(
  ) {
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::Future<FMGL(T)> >)this);
    
}

//#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::util::Future<FMGL(T)>::__fieldInitializers2089(
  ) {
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
      FMGL(root) = x10::lang::GlobalRef_methods<x10aux::ref<x10::util::Future<FMGL(T)> > >::_make(((x10aux::ref<x10::util::Future<FMGL(T)> >)this));
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
      FMGL(latch) = x10::lang::Latch::_make();
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
      FMGL(exception) = x10::util::GrowableRail<x10aux::ref<x10::lang::Throwable> >::_make();
    
    //#line 27 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::Future<FMGL(T)> >)this)->
      FMGL(result) = x10::util::GrowableRail<FMGL(T) >::_make();
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::util::Future<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::Future<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::util::Future<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(root));
    
}

template<class FMGL(T)> void x10::util::Future<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(root) = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::util::Future<FMGL(T)> > > >();
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::util::Future<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::util::Future<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::Future<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[2] = { x10aux::getRTT<x10::lang::Object>(), x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.Future";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 2, parents, 1, params, variances);
}
template<class FMGL(T)> x10aux::ref<x10::util::Future<FMGL(T)> >
  x10::util::Future<void>::make(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > eval)
{
    
    //#line 46 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::ensureNotInAtomic();
    
    //#line 47 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::Future<FMGL(T)> > f =
      x10::util::Future<FMGL(T)>::_make(eval);
    
    //#line 48 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::runAsync(
      x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_util_Future__closure__3<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_util_Future__closure__3<FMGL(T)>)))x10_util_Future__closure__3<FMGL(T)>(f)))));
    
    //#line 49 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/Future.x10": x10.ast.X10Return_c
    return f;
    
}
#endif // X10_UTIL_FUTURE_H_IMPLEMENTATION
#endif // __X10_UTIL_FUTURE_H_NODEPS
